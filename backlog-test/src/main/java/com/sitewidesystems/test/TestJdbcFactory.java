package com.sitewidesystems.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

/**
 * This was provided during a spring workshop to help do in-memory db testing of jdbc
 * Repositories. Very useful.
 * User: gstewar8
 * Date: Jun 20, 2008
 * Time: 11:58:17 AM
 */
public class TestJdbcFactory implements FactoryBean, InitializingBean {

    private Log logger = LogFactory.getLog(getClass());

	// configurable properties

	private String testDatabaseName;
	private Resource schemaLocation;
	private Resource testDataLocation;

	/**
	 * The object created by this factory.
	 */
	private DataSource dataSource;

	/**
	 * Creates a new TestDataSourceFactory for use in "bean" style. "Bean" style means the default constructor is called
	 * and then properties are set to configure this object. "Bean" style usage is nice when this object is defined as a
	 * Spring bean, as setter-injection can be more descriptive than constructor-injection from the point of view of a
	 * bean definition author.
	 * @see {@link #setTestDatabaseName(String)}
	 * @see {@link #setSchemaLocation(Resource)}
	 * @see {@link #setTestDataLocation(Resource)}
	 */
	public TestJdbcFactory() {
	}

	/**
	 * Creates a new TestDataSourceFactory fully-initialized with what it needs to work. Fully-formed constructors are
	 * nice in a programmatic environment, as they result in more concise code and allow for a class to enforce its
	 * required properties.
	 * @param testDatabaseName the name of the test database to create
	 * @param schemaLocation the location of the file containing the schema DDL to export to the database
	 * @param testDataLocation the location of the file containing the test data to load into the database
	 */
	public TestJdbcFactory(String testDatabaseName, Resource schemaLocation, Resource testDataLocation) {
		setTestDatabaseName(testDatabaseName);
		setSchemaLocation(schemaLocation);
		setTestDataLocation(testDataLocation);
	}

	/**
	 * Sets the name of the test database to create.
	 * @param testDatabaseName the name of the test database, i.e. "rewards"
	 */
	public void setTestDatabaseName(String testDatabaseName) {
		this.testDatabaseName = testDatabaseName;
	}

	/**
	 * Sets the location of the file containing the schema DDL to export to the test database.
	 * @param schemaLocation the location of the database schema DDL
	 */
	public void setSchemaLocation(Resource schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

	/**
	 * Sets the location of the file containing the test data to load into the database.
	 * @param testDataLocation the location of the test data file
	 */
	public void setTestDataLocation(Resource testDataLocation) {
		this.testDataLocation = testDataLocation;
	}

	// implementing InitializingBean

	// this method is automatically called by Spring after configuration to perform a dependency check and init
	public void afterPropertiesSet() {
		if (testDatabaseName == null) {
			throw new IllegalArgumentException("The name of the test database to create is required");
		}
		if (schemaLocation == null) {
			throw new IllegalArgumentException("The path to the database schema DDL is required");
		}
		if (testDataLocation == null) {
			throw new IllegalArgumentException("The path to the test data set is required");
		}
		initDataSource();
	}

	// implementing FactoryBean

	// this method is automatically called by Spring to expose the DataSource as a bean
	public Object getObject() throws Exception {
		return getDataSource();
	}

	public Class getObjectType() {
		return DataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	// other methods

	/**
	 * Factory method that returns the fully-initialized test data source. Useful when this class is used
	 * programatically instead of deployed as a Spring bean.
	 * @return the data source
	 */
	public DataSource getDataSource() {
		if (dataSource == null) {
			initDataSource();
		}
		return dataSource;
	}

	// static factory methods

	/**
	 * Static factory method that creates a DataSource that connects to a test database populated with test data.
	 * @param testDatabaseName the name of the test database to create
	 * @param schemaLocation the database schema to export
	 * @param testDataLocation the database test data to load
	 * @return the data source
	 */
	public static DataSource createDataSource(String testDatabaseName, Resource schemaLocation,
			Resource testDataLocation) {
		return new TestJdbcFactory(testDatabaseName, schemaLocation, testDataLocation).getDataSource();
	}

	// internal helper methods

	// encapsulates the steps involved in initializing the data source: creating it, and populating it
	private void initDataSource() {
		// create the in-memory database source first
		this.dataSource = createDataSource();
		if (logger.isDebugEnabled()) {
			logger.debug("Created in-memory test database '" + testDatabaseName + "'");
		}
		// now populate the database by loading the schema and test data
		populateDataSource();
		if (logger.isDebugEnabled()) {
			logger.debug("Exported schema in " + schemaLocation);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Loaded test data in " + testDataLocation);
		}
	}

	private DataSource createDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// use the HsqlDB JDBC driver
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		// have it create an in-memory database
		dataSource.setUrl("jdbc:hsqldb:mem:" + testDatabaseName);
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	private void populateDataSource() {
		TestDatabasePopulator populator = new TestDatabasePopulator(dataSource);
		populator.populate();
	}

	/**
	 * Populates a in memory data source with test data.
	 */
	private class TestDatabasePopulator {

		private DataSource dataSource;

		/**
		 * Creates a new test database populator.
		 * @param dataSource the test data source that will be populated.
		 */
		public TestDatabasePopulator(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		/**
		 * Populate the test database by creating the database schema from 'schema.sql' and inserting the test data in
		 * 'testdata.sql'.
		 */
		public void populate() {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				createDatabaseSchema(connection);
				insertTestData(connection);
			} catch (SQLException e) {
				throw new RuntimeException("SQL exception occurred acquiring connection", e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
					}
				}
			}
		}

		// create the application's database schema (tables, indexes, etc.)
		private void createDatabaseSchema(Connection connection) {
			try {
				String sql = parseSqlIn(schemaLocation);
				executeSql(sql, connection);
			} catch (IOException e) {
				throw new RuntimeException("I/O exception occurred accessing the database schema file", e);
			} catch (SQLException e) {
				throw new RuntimeException("SQL exception occurred exporting database schema", e);
			}
		}

		// populate the tables with test data
		private void insertTestData(Connection connection) {
			try {
				String sql = parseSqlIn(testDataLocation);
				executeSql(sql, connection);
			} catch (IOException e) {
				throw new RuntimeException("I/O exception occurred accessing the test data file", e);
			} catch (SQLException e) {
				throw new RuntimeException("SQL exception occurred loading test data", e);
			}
		}

		// utility method to read a .sql txt input stream
        private String parseSqlIn(Resource resource) throws IOException {
			InputStream is = null;
			try {
                is = resource.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));

				StringWriter sw = new StringWriter();
				BufferedWriter writer = new BufferedWriter(sw);

				for (int c=reader.read(); c != -1; c=reader.read()) {
					writer.write(c);
				}
				writer.flush();
				return sw.toString();

			} finally {
				if (is != null) {
					is.close();
				}
			}
		}

		// utility method to run the parsed sql
		private void executeSql(String sql, Connection connection) throws SQLException {
			Statement statement = connection.createStatement();
			statement.execute(sql);
		}

	}}

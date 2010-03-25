package com.sitewidesystems.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.h2.Driver;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 10:23:53 AM
 */
public class TestH2Factory implements FactoryBean, InitializingBean {
    private Log logger = LogFactory.getLog(getClass());

    private String testDatabaseName;
    private Resource schemaLocation;
    private Resource testDataLocation;

    private DataSource dataSource;

    /**
     * <p>
     * Creates a new H2 test factory. It uses the "bean" style. This means create the object then use
     * setters to initialize. The bean will require:
     * </p>
     * <p>
     * <ol>
     * <li>A database name. Used to create the database files.</li>
     * <li>A schema file. Used to setup the database structure.</li>
     * <li>A data file. Used to setup the initial data.</li>
     * </ol>
     * </p>
     */
    public TestH2Factory() {

    }

    /**
     * Used to create the bean using constructor arguments. It requires a string for the database name,
     * a spring Resource with the location of a schema sql file and
     *
     * @param dbName
     * @param schemaLocation
     * @param datafileLocation
     */
    public TestH2Factory(String dbName, Resource schemaLocation, Resource datafileLocation) {
        setTestDatabaseName(dbName);
        setSchemaLocation(schemaLocation);
        setTestDataLocation(datafileLocation);
    }

    protected String getTestDatabaseName() {
        return testDatabaseName;
    }

    /**
     * Set the name of the database you want to create.
     *
     * @param testDatabaseName
     */
    @Required
    public void setTestDatabaseName(String testDatabaseName) {
        this.testDatabaseName = testDatabaseName;
    }

    protected Resource getSchemaLocation() {
        return schemaLocation;
    }

    /**
     * Provide a resource object with the file that contains the sql statements used to construct
     * the database.
     *
     * @param schemaLocation
     */
    @Required
    public void setSchemaLocation(Resource schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    protected Resource getTestDataLocation() {
        return testDataLocation;
    }

    @Required
    public void setTestDataLocation(Resource testDataLocation) {
        this.testDataLocation = testDataLocation;
    }

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

    @Override
    public Object getObject() throws Exception {
        return getDataSource();
    }

    @Override
    public Class getObjectType() {
        return DataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static DataSource createDataSource(String testDatabaseName, Resource schemaLocation,
            Resource testDataLocation) {
        return new TestH2Factory(testDatabaseName, schemaLocation, testDataLocation).getDataSource();
    }

    public DataSource getDataSource() {
        if (dataSource == null) {
            initDataSource();
        }
        return dataSource;
    }
    
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
        dataSource.setDriverClassName("org.h2.Driver");
        // have it create an in-memory database
        dataSource.setUrl("jdbc:h2:mem:" + testDatabaseName);
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private void populateDataSource() {
        DBPopulator populator = new DBPopulator(dataSource);
        try {
            populator.generate(dataSource.getConnection(), getSchemaLocation());
            populator.generate(dataSource.getConnection(), getTestDataLocation());
        } catch (SQLException e) {
            logger.error("SQL Exception thrown while building in-mem database",e);
        } catch (RuntimeException e) {
            logger.error("Runtime Exception thrown while building in-mem database",e);
        }
    }

    protected class DBPopulator {

        private DataSource dataSource;

        public DBPopulator(DataSource ds) {
            dataSource = ds;
        }

        public void generate(Connection c, Resource r) throws RuntimeException {
            try {
                String sql = parseSql(r);
                Statement statement = c.createStatement();
                statement.execute(sql);
            } catch (SQLException e) {
                logger.error("SQL Exception encountered", e);
                RuntimeException dbe = new RuntimeException(e.getMessage());
                throw dbe;
            } catch (IOException e) {
                logger.error("Error reading file", e);
                RuntimeException dbe = new RuntimeException(e.getMessage());
                throw dbe;
            }


        }

        public String parseSql(Resource r) throws IOException {
            InputStream f = null;
            try {
                f = r.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(f));

                StringWriter sw = new StringWriter();
                BufferedWriter writer = new BufferedWriter(sw);

                for (int l = reader.read(); l != -1; l = reader.read()) {
                    writer.write(l);
                }
                writer.flush();
                return sw.toString();

            } catch (IOException e) {
                logger.error("Exception thrown while reading file: " + r.getFilename(), e);
                throw e;
            } finally {
                if (f != null) {
                    f.close();
                }
            }
        }
    }
}

package com.sitewidesystems.backlog.client.util;

import org.junit.Test;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 2:32:32 PM
 */
public class PathManipulatorTest {

    String testPathEven = "/key/value/path/object";
    String testPathOdd = "/key/value/other/stuff/odd";
    String testPathRepeat = "/key/value/key/otherValue";

    MockHttpServletRequest request = new MockHttpServletRequest();
    HashMap<String,String> keyVals = new HashMap<String, String>();
    PathManipulator pathManipulator = new PathManipulator();


    @Before
    public void setupElements () {
        request = new MockHttpServletRequest();
        keyVals = new HashMap<String, String>();
        pathManipulator = new PathManipulator();
    }

    @After
    public void cleanElements() {
        request.setPathInfo("");
        keyVals = new HashMap<String, String>();
        pathManipulator = new PathManipulator();

    }

    @Test
    public void keyValuesEvenPath() {
        request.setPathInfo(testPathEven);
        keyVals = pathManipulator.keyValues(request);

        System.out.println("Keys:"+keyVals.keySet().size());
        Assert.assertEquals(2,keyVals.size());
        Assert.assertEquals("value",keyVals.get("key"));
        Assert.assertEquals("object",keyVals.get("path"));
    }

    @Test
    public void keyValuesOddPath() {

        request.setPathInfo(testPathOdd);
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(3,keyVals.size());
        Assert.assertEquals("value",keyVals.get("key"));
        Assert.assertEquals("stuff",keyVals.get("other"));
        Assert.assertEquals(null,keyVals.get("odd"));

    }

    @Test
    public void keyValuesRepeatPath () {
        request.setPathInfo(testPathRepeat);
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(1,keyVals.size());
        Assert.assertEquals("otherValue",keyVals.get("key"));
    }

    @Test
    public void keyValuesNullPath () {

        request.setPathInfo(null);
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(0,keyVals.size());
//        Assert.assertEquals("otherValue",keyVals.get("key"));
    }

    @Test
    public void keyValuesEmptyPath () {
        request.setPathInfo("");
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(0,keyVals.size());
    }

    @Test
    public void keyValuesEmptyPathElements () {
        request.setPathInfo("key/value//key2//value///hello/object/odd/");
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals("value",keyVals.get("key"));
        Assert.assertEquals("value",keyVals.get("key2"));
        Assert.assertEquals("object",keyVals.get("hello"));
        Assert.assertEquals(null,keyVals.get("odd"));
        Assert.assertEquals(4,keyVals.size());

    }

    @Test
    public void keyValuesEvenPathWithOffset() {
        request.setPathInfo(testPathEven);
        pathManipulator.setDefaultOffset(1);
        keyVals = pathManipulator.keyValues(request);

        System.out.println("Keys:"+keyVals.keySet().size());
        Assert.assertEquals(2,keyVals.size());
        Assert.assertEquals("path",keyVals.get("value"));
        Assert.assertEquals(null,keyVals.get("object"));
    }

    @Test
    public void keyValuesOddPathWithOffset() {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo(testPathOdd);
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(2,keyVals.size());
        Assert.assertEquals("other",keyVals.get("value"));
        Assert.assertEquals("odd",keyVals.get("stuff"));

    }

    @Test
    public void keyValuesRepeatPathWithOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo(testPathRepeat);
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(2,keyVals.size());
        Assert.assertEquals("key",keyVals.get("value"));
        Assert.assertEquals(null,keyVals.get("otherValue"));
    }

    @Test
    public void keyValuesNullPathWithOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo(null);
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(0,keyVals.size());
//        Assert.assertEquals("otherValue",keyVals.get("key"));
    }

    @Test
    public void keyValuesEmptyPathWithOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo("");
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals(0,keyVals.size());
    }

    @Test
    public void keyValuesEmptyPathElementsWithOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo("key/value//key2//value///hello/object/odd/");
        keyVals = pathManipulator.keyValues(request);

        Assert.assertEquals("hello",keyVals.get("value"));
        Assert.assertEquals("odd",keyVals.get("object"));
        Assert.assertEquals(2,keyVals.size());

    }

    @Test
    public void keyValuesEmptyPathElementsWithExtraOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo("key/value//key2//value///hello/object/odd/");
        keyVals = pathManipulator.keyValues(request,1);

        Assert.assertEquals("value",keyVals.get("key2"));
        Assert.assertEquals("object",keyVals.get("hello"));
        Assert.assertEquals(null,keyVals.get("odd"));
        Assert.assertEquals(3,keyVals.size());

    }

    @Test
    public void keyValuesEmptyPathElementsWithNegativeOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo("key/value//key2//value///hello/object/odd/");
        keyVals = pathManipulator.keyValues(request,-1);

        Assert.assertEquals("value",keyVals.get("key"));
        Assert.assertEquals("value",keyVals.get("key2"));
        Assert.assertEquals("object",keyVals.get("hello"));
        Assert.assertEquals(null,keyVals.get("odd"));
        Assert.assertEquals(4,keyVals.size());

    }

    @Test
    public void keyValuesEmptyPathElementsWithExcessiveOffset () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo("key/value//key2//value///hello/object/odd/");
        keyVals = pathManipulator.keyValues(request,-9);

        Assert.assertEquals("value",keyVals.get("key"));
        Assert.assertEquals("value",keyVals.get("key2"));
        Assert.assertEquals("object",keyVals.get("hello"));
        Assert.assertEquals(null,keyVals.get("odd"));
        Assert.assertEquals(4,keyVals.size());

    }

    @Test
    public void keyValueWithSeparatorOnly () {
        pathManipulator.setDefaultOffset(1);
        request.setPathInfo("/");
        keyVals = pathManipulator.keyValues(request,0);

        Assert.assertEquals(0,keyVals.size());

    }

    @Test
    public void keyValuesWithLeadingSeparator () {
        pathManipulator.setIgnoreLeadingSeparator(false);
        pathManipulator.setDefaultOffset(0);
        request.setPathInfo("/key/value//key2//value///hello/object/odd/");
        keyVals = pathManipulator.keyValues(request,-9);

        Assert.assertEquals("value",keyVals.get("key"));
        Assert.assertEquals("value",keyVals.get("key2"));
        Assert.assertEquals("object",keyVals.get("hello"));
        Assert.assertEquals(null,keyVals.get("odd"));
        Assert.assertEquals(4,keyVals.size());

    }
}

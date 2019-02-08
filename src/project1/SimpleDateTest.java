package project1;



import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 *
 *
 * @author clinthof
 *
 */

public class SimpleDateTest {

    @Test
    public void testDefaultConstructor1() {

        SimpleDate d = new SimpleDate();
        assertTrue(d.getDay() == 0 &&
                d.getMonth() == 0 && d.getYear() == 0);
    }

    @Test
    public void testDefaultConstructor2() {

        SimpleDate d = new SimpleDate();
        assertFalse(d.getDay() == 99 &&
                d.getMonth() == 99 && d.getYear() == 9999);
    }

    @Test
    public void testDefaultConstructor3() {

        SimpleDate d = new SimpleDate();
        assertFalse(d.getDay() == -1 &&
                d.getMonth() == -1 && d.getYear() == -1754);
    }

    @Test
    public void testIsLeapYear1() {

        SimpleDate d = new SimpleDate("3/1/2013");
        assertFalse(d.isLeapYear());
    }

    @Test
    public void testIsLeapYear2() {
        SimpleDate d = new SimpleDate("3/1/2016");
        assertTrue(d.isLeapYear());
    }

    @Test
    public void testToString() {
        SimpleDate d1 = new SimpleDate("2/10/2000");
        assertEquals("10 February  2000", d1.toString());
        d1 = new SimpleDate("7/06/1957");
        assertEquals("06 July  1957", d1.toString());
   }

    @Test
    public void testCompareTo1() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/3000");
        assertTrue(d1.compareTo(d2) < 0);
    }

    @Test
    public void testCompareTo2() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/2000");
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    public void testCompareTo3() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("3/1/2013");
        assertEquals(d1, d2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectYear1() {
        SimpleDate d1 = new SimpleDate("3/01/1700");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectYear2() {
        SimpleDate d1 = new SimpleDate("3/01/1700");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectMonth1() {
        SimpleDate d1 = new SimpleDate("-3/01/2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectMonth2() {
        SimpleDate d1 = new SimpleDate("18/01/2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectMonth3() {
        SimpleDate d1 = new SimpleDate("0/01/2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectDay1() {
        SimpleDate d1 = new SimpleDate("3/-1/2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectDay2() {
        SimpleDate d1 = new SimpleDate("3/32/2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectDay3() {
        SimpleDate d1 = new SimpleDate("3/0/2000");
    }

    @Test
    public void testIncrement1() {
        SimpleDate d1 = new SimpleDate("5/11/2018");
        SimpleDate d2 = new SimpleDate("5/12/2018");

        d1.increment();

        assertTrue(d1.compareTo(d2) == 0);
    }

    @Test
    public void testIncrement2(){
        SimpleDate d1 = new SimpleDate("7/28/2010");
        d1.increment();
        SimpleDate d2 = new SimpleDate("7/29/2010");
        assertTrue(d1.equals(d2));
    }

    @Test
    public void testMultiInc() {

        int i;

        SimpleDate d1 = new SimpleDate("1/1/2000");
        SimpleDate d2 = new SimpleDate("2/1/2000");

        for (i = 1; i <= 31; i++) {
            d1.increment();
        }

        assertEquals(d1, d2);
    }

    @Test
    public void testNewYear1() {

        SimpleDate d1 = new SimpleDate("12/31/2018");
        SimpleDate d2 = new SimpleDate("1/1/2019");

        d1.increment();

        assertEquals(d1, d2);
    }

    @Test
    public void testDecrement1() {

        SimpleDate d1 = new SimpleDate("5/12/2018");
        SimpleDate d2 = new SimpleDate("5/11/2018");

        d1.decrement();

        assertTrue(d1.compareTo(d2) == 0);
    }

    @Test
    public void testNewYear2() {

        SimpleDate d1 = new SimpleDate("12/31/2018");
        SimpleDate d2 = new SimpleDate("1/1/2019");

        d2.decrement();

        assertEquals(d1, d2);
    }

    @Test
    public void setterTest() {

        SimpleDate d1 = new SimpleDate("9/5/2018");

        assertTrue(d1.getDay() == 5 && d1.getMonth() == 9
        && d1.getYear() == 2018);
    }

    @Test
    public void trickSetter() {

        SimpleDate d1 = new SimpleDate("8/4/2017");
        assertFalse(d1.getDay() == 5 && d1.getMonth() == 9
                && d1.getYear() == 2018);
    }

    @Test
    public void getDaysTest1() {
        assertEquals(SimpleDate.getDaysInMonth(1,2000),31);
    }

    @Test
    public void getDaysLeapTest1() {
        assertEquals(SimpleDate.getDaysInMonth(2,2000), 29);
    }

    @Test
    public void constructorEquals1() {

        SimpleDate d1 = new SimpleDate(2,10,1998);
        SimpleDate d2 = new SimpleDate("2/10/1998");

        assertTrue(d1.equals(d2));
    }

    @Test
    public void trickEquals2() {

        SimpleDate d1 = new SimpleDate(2,10,1998);
        SimpleDate d2 = new SimpleDate("2/11/1998");

        assertFalse(d1.equals(d2));
    }

    @Test
    public void daysSince1() {

        SimpleDate d1 = new SimpleDate("8/15/2001");
        SimpleDate d2 = new SimpleDate("8/16/2001");

        assertEquals(d1.daysSince(d2),  0);
    }

    @Test
    public void daysSince2() {

        SimpleDate d1 = new SimpleDate("8/15/2001");
        SimpleDate d2 = new SimpleDate("8/25/2001");

        assertEquals(d1.daysSince(d2),  0);
    }

    @Test
    public void saveFile1(){

        SimpleDate d1 = new SimpleDate("1/1/1995");

        d1.save("file1");
        d1 = new SimpleDate("1/1/2016");
        d1.load("file1");

        assertEquals(d1.getDay(),1);
        assertEquals(d1.getMonth(),1);
        assertEquals(d1.getYear(),1995);
    }

    @Test
    public void loadFile(){

        SimpleDate d1 = new SimpleDate("4/20/2016");

        d1.save("file1");
        d1 = new SimpleDate("2/20/2015");
        d1.load("file1");

        assertEquals(d1.getDay(),20);
        assertEquals(d1.getMonth(),4);
        assertEquals(d1.getYear(),2016);
    }
}
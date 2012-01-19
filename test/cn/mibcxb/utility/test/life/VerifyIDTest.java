/**
 * 
 */
package cn.mibcxb.utility.test.life;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.mibcxb.utility.life.VerifyID;

/**
 * @author CXB
 *
 */
public class VerifyIDTest {

	/**
	 * Test method for {@link cn.mibcxb.utility.life.VerifyID#verifyID(java.lang.String)}.
	 */
	@Test
	public void testVerifyID() {
		String id = "input id number";
		assertTrue(VerifyID.verifyID(id));
	}

}

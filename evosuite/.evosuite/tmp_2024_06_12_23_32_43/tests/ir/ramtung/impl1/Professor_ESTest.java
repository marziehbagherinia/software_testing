/*
 * This file was automatically generated by EvoSuite
 * Wed Jun 12 20:18:15 GMT 2024
 */

package ir.ramtung.impl1;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import ir.ramtung.impl1.Professor;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Professor_ESTest extends Professor_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Professor professor0 = new Professor("SZZew");
      int int0 = professor0.allowedToBorrow();
      assertEquals(5, int0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Professor professor0 = null;
      try {
        professor0 = new Professor("");
        fail("Expecting exception: Exception");
      
      } catch(Throwable e) {
         //
         // Empty member name not allowed
         //
         verifyException("ir.ramtung.impl1.Member", e);
      }
  }
}

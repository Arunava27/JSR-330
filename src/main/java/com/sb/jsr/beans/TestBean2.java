package com.sb.jsr.beans;

import javax.inject.Named;
import javax.inject.Singleton;

/* @Inject JSR#330 annotation equivalent to Spring @Component. To make bean singleton in JSR#330 environment we need 
 * mark the bean with @Sigleton explicitly
 *  */

@Named
@Singleton
public class TestBean2 {

}

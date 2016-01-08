/**
 * 
 */
package com.frain.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frain.model.RespModel;

/**
 * @author kalven.meng
 *
 */
public interface Controller {
	RespModel service(String path, HttpServletRequest request, HttpServletResponse response);
}

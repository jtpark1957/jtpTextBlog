package com.jtp.jtpTextBlog.controller;

public abstract class Controller {
	public abstract void doCommand(String cmd);
	public abstract String ret(String cmd);
}
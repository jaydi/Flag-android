package com.tankers.smile.services;

import java.io.IOException;

public abstract class Work<T> {
	public abstract T work() throws IOException;
}

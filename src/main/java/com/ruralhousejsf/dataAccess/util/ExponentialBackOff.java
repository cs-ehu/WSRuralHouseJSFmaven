package com.ruralhousejsf.dataAccess.util;
import static java.util.Arrays.asList;

import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import java.net.ConnectException;

public final class ExponentialBackOff {

	private static final int[] FIBONACCI = new int[] {1, 1, 2, 3, 5, 8, 13, 21};

	private static final List<Class<? extends Exception>> EXPECTED_COMMUNICATION_ERRORS = asList(
			SSLHandshakeException.class,
			RemoteException.class,
			SocketTimeoutException.class,
			ConnectException.class,
			SQLTimeoutException.class,
			SQLException.class
	);

	private ExponentialBackOff() {}

	public static <T> T execute(ExponentialBackOffInterface<T> method) {
		return execute(method, "Failed to establish communication.");
	}

	public static <T> T execute(ExponentialBackOffInterface<T> method, String exceptionMessage) {
		for (int attempt = 0; attempt < FIBONACCI.length; attempt++) {
			try {
				return method.execute();
			} catch (Exception e) {
				System.err.println("\n" + e.getMessage() + "\nRetrying operation in " + FIBONACCI[attempt] + "s.");
				handleFailure(attempt, e);
			}
		}
		throw new RuntimeException(exceptionMessage);
	}

	private static void handleFailure(int attempt, Throwable e) {
		if (e.getCause() != null && !EXPECTED_COMMUNICATION_ERRORS.contains(e.getCause().getClass())) {
			throw new RuntimeException(e);
		}
		wait(attempt);
	}

	private static void wait(int attempt) {
		try {
			Thread.sleep(FIBONACCI[attempt] * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}

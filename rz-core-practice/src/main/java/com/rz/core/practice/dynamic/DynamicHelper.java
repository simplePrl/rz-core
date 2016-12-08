package com.rz.core.practice.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicHelper {

	public static void main(String[] args) {
		DynamicHelper dynamicHelper = new DynamicHelper();
		dynamicHelper.testProxy1();
	}

	private void testProxy1() {
		IWorker worker = new Worker();
		InvocationHandler invocationHandler = new DefaultInvocationHandler(worker);
		IWorker workerProxy = (IWorker) Proxy.newProxyInstance(IWorker.class.getClassLoader(),
				new Class[] { IWorker.class }, invocationHandler);

		if (null != workerProxy) {
			workerProxy.run(666);
		}

		invocationHandler = Proxy.getInvocationHandler(workerProxy);
		try {
			invocationHandler.invoke(workerProxy, 
					worker.getClass().getMethod("run", new Class[] { int.class }), 
					new Object[] { 666 });
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("worker: " + Proxy.isProxyClass(worker.getClass()) + "   " + "workerProxy: " + Proxy.isProxyClass(workerProxy.getClass()));
	}

	private void testProxy2() {
		IWorker worker = new Worker();
		InvocationHandler invocationHandler = new DefaultInvocationHandler(worker);
		Class<?> classProxy = Proxy.getProxyClass(IWorker.class.getClassLoader(), new Class[] { IWorker.class });
		// classProxy class have a constructor method like [void
		// $Proxy0(InvocationHandler invocationHandler)]
		IWorker workerProxy = null;
		try {
			workerProxy = (IWorker) classProxy.getConstructor(new Class[] { InvocationHandler.class })
					.newInstance(new Object[] { invocationHandler });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != workerProxy) {
			workerProxy.run(666);
		}
	}
}

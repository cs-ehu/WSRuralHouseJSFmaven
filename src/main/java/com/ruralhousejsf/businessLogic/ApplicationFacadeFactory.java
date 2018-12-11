package com.ruralhousejsf.businessLogic;

import com.ruralhousejsf.dataAccess.HibernateDataAccess;

public final class ApplicationFacadeFactory {
	
	public ApplicationFacadeFactory() {
	}

	public static ApplicationFacadeInterface createApplicationFacade() {
		ApplicationFacadeInterface aplicationFacade = null;
		aplicationFacade = new ApplicationFacadeImpl(new HibernateDataAccess());
		return aplicationFacade;
	}
}

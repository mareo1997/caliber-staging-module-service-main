package com.revature.backend;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.revature.backend.service.ManagerService;
import com.revature.backend.util.StagingListener;


/**
 * This class acts as a starter-motor for the ManagerLoadBalancing system. Once Spring completely finishes loading, it'll start the {@link StagingListener}.
 *  
 * Any other methods that should run on startup should also be called here to prevent conflict with Spring's bootup.
 * @author Ben Johnston
 *
 */
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	StagingListener sListener;
	@Autowired
	ManagerService mServ;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
		//-------------		
		//Starts the periodic checking for new batches. Will run immediately on server start up, then weekly on the specified UpdateDay until the server is restarted.
		sListener.startListening();
	}	
	
}

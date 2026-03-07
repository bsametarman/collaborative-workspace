package com.collaborativeworkspace.snapshot_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SnapshotWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnapshotWorkerApplication.class, args);
	}

}

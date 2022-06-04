package com.gmakris.matchmanager.service.properties;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Defines a {@link reactor.core.scheduler.Scheduler} to be used for blocking operations.
 */
@Data
@NoArgsConstructor
public class SchedulerProperties {

    /**
     * Maximum number of threads to be used.
     */
    private int threads;

    /**
     * Scheduler buffer. Utilized to hold elements waiting
     * for a blocking operation if all threads are tied up.
     */
    private int bufferSize;

    /**
     * Number of seconds an operation may remain in the buffer,
     * awaiting a thread to open up, before it gets dropped.
     */
    private int secondsToLive;
}

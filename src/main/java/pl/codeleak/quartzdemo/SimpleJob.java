package pl.codeleak.quartzdemo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeleak.quartzdemo.ejb.SimpleEjb;

import javax.ejb.EJB;
import javax.inject.Named;
import java.text.SimpleDateFormat;

public class SimpleJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleJob.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @EJB
    private SimpleEjb simpleEjb;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LOG.info("Instance: {}, Trigger: {}, Fired at: {}",
                    context.getScheduler().getSchedulerInstanceId(),
                    context.getTrigger().getKey(),
                    sdf.format(context.getFireTime()));
        } catch (SchedulerException e) {
            // intentionally left blank
        }
        simpleEjb.doSomething();
    }
}

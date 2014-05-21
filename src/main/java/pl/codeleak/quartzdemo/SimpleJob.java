package pl.codeleak.quartzdemo;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeleak.quartzdemo.ejb.SimpleEjb;

import javax.ejb.EJB;
import java.text.SimpleDateFormat;

@DisallowConcurrentExecution
@ExecuteInJTATransaction
public class SimpleJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger("MyJob");
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @EJB
    private SimpleEjb simpleEjb;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LOG.info("Trigger: {}, Fired at: {}, Instance: {}",
                    context.getTrigger().getKey(),
                    sdf.format(context.getFireTime()),
                    context.getScheduler().getSchedulerInstanceId());
        } catch (SchedulerException e) {
            // intentionally left blank
        }
        simpleEjb.doSomething();
    }
}

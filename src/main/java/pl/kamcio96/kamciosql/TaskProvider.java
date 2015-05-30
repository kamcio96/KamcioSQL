package pl.kamcio96.kamciosql;

import java.util.logging.Logger;

public interface TaskProvider {

    public Logger getLogger();

    public void runTask(Runnable runnable);

    public void runTaskTimer(Runnable runnable, long peroid);

}

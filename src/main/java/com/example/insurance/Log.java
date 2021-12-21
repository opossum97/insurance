package com.example.insurance;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private Logger logger = Logger.getLogger(Log.class.getName());

    public Log(Handler handler) {
        this.logger.setUseParentHandlers(false);
        this.logger.addHandler(handler);
    }

    public void infoWrite(String msg, Class className, String method) {
        this.logger.setLevel(Level.FINE);
        this.logger.logp(Level.INFO, className.getName(), method, msg);
    }

    public void fineWrite(String msg, Class className, String method) {
        this.logger.setLevel(Level.FINE);
        this.logger.logp(Level.FINE, className.getName(), method, msg);
    }

    public void warningWrite(String msg, Class className, String method) {
        this.logger.setLevel(Level.FINE);
        this.logger.logp(Level.WARNING, className.getName(), method, msg);
    }

    public void severeWrite(String msg, Class className, String method) {
        this.logger.setLevel(Level.FINE);
        this.logger.logp(Level.SEVERE, className.getName(), method, msg);
    }
}
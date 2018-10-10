package com.mwee.android.print.processor.command;

import com.mwee.android.print.base.PrinterConfig;

/**
 * Created by virgil on 2018/1/25.
 *
 * @author virgil
 */
public class CommandProxy {

    public static ICommand createCommand(PrinterConfig config) {
        if (config.starPrinter()) {
            return new CommandStar();
        } else {
            return new CommandEsc();
        }
    }
}

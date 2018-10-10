/* Copyright 2011-2013 Google Inc.
 * Copyright 2013 mike wakerly <opensource@hoho.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General protected
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General protected License for more details.
 *
 * You should have received a copy of the GNU Lesser General protected
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * Project home page: https://github.com/mik3y/usb-serial-for-android
 */

package com.mwee.android.print.printer.usbserial.driver;

/**
 * Registry of USB vendor/product ID constants.
 * <p>
 * Culled from various sources; see
 * <a href="http://www.linux-usb.org/usb.ids">usb.ids</a> for one listing.
 *
 * @author mike wakerly (opensource@hoho.com)
 */
final class UsbId {

    protected static final int VENDOR_FTDI = 0x0403;
    protected static final int FTDI_FT232R = 0x6001;
    protected static final int FTDI_FT231X = 0x6015;

    protected static final int VENDOR_ATMEL = 0x03EB;
    protected static final int ATMEL_LUFA_CDC_DEMO_APP = 0x2044;

    protected static final int VENDOR_ARDUINO = 0x2341;
    protected static final int ARDUINO_UNO = 0x0001;
    protected static final int ARDUINO_MEGA_2560 = 0x0010;
    protected static final int ARDUINO_SERIAL_ADAPTER = 0x003b;
    protected static final int ARDUINO_MEGA_ADK = 0x003f;
    protected static final int ARDUINO_MEGA_2560_R3 = 0x0042;
    protected static final int ARDUINO_UNO_R3 = 0x0043;
    protected static final int ARDUINO_MEGA_ADK_R3 = 0x0044;
    protected static final int ARDUINO_SERIAL_ADAPTER_R3 = 0x0044;
    protected static final int ARDUINO_LEONARDO = 0x8036;
    protected static final int ARDUINO_MICRO = 0x8037;

    protected static final int VENDOR_VAN_OOIJEN_TECH = 0x16c0;
    protected static final int VAN_OOIJEN_TECH_TEENSYDUINO_SERIAL = 0x0483;

    protected static final int VENDOR_LEAFLABS = 0x1eaf;
    protected static final int LEAFLABS_MAPLE = 0x0004;

    protected static final int VENDOR_SILABS = 0x10c4;
    protected static final int SILABS_CP2102 = 0xea60;
    protected static final int SILABS_CP2105 = 0xea70;
    protected static final int SILABS_CP2108 = 0xea71;
    protected static final int SILABS_CP2110 = 0xea80;

    protected static final int VENDOR_PROLIFIC = 0x067b;
    protected static final int PROLIFIC_PL2303 = 0x2303;

    protected static final int VENDOR_QINHENG = 0x1a86;
    protected static final int QINHENG_HL340 = 0x7523;

    private UsbId() {
        throw new IllegalAccessError("Non-instantiable class.");
    }

}

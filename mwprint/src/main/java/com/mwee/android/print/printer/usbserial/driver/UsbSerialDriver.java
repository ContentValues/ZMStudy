/* Copyright 2011-2013 Google Inc.
 * Copyright 2013 mike wakerly <opensource@hoho.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General 
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General  License for more details.
 *
 * You should have received a copy of the GNU Lesser General 
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * Project home page: https://github.com/mik3y/usb-serial-for-android
 */

package com.mwee.android.print.printer.usbserial.driver;

import android.hardware.usb.UsbDevice;

import java.util.List;
import java.util.Map;

/**
 * @author mike wakerly (opensource@hoho.com)
 */
public interface UsbSerialDriver {

    /**
     * Returns the raw {@link UsbDevice} backing this port.
     *
     * @return the device
     */
    UsbDevice getDevice();

    /**
     * Returns all available ports for this device. This list must have at least
     * one entry.
     *
     * @return the ports
     */
    List<UsbSerialPort> getPorts();

    /**
     * 获取所有支持的设备
     * @return Map<Integer, int[]>
     */
    Map<Integer, int[]> getSupportedDevices();

}
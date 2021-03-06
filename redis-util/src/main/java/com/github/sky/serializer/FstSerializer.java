package com.github.sky.serializer;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import redis.clients.util.SafeEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Description:FstSerializer的方式实现序列化
 * Author: sukai
 * Date: 2017-08-11
 */
public class FstSerializer implements ISerializer {

    public static final ISerializer fstSerializer = new FstSerializer();

    public byte[] keyToBytes(String key) {
        return SafeEncoder.encode(key);
    }

    public String keyFromBytes(byte[] bytes) {
        return SafeEncoder.encode(bytes);
    }

    public byte[] fieldToBytes(Object field) {
        return valueToBytes(field);
    }

    public Object fieldFromBytes(byte[] bytes) {
        return valueFromBytes(bytes);
    }

    public byte[] valueToBytes(Object value) {
        FSTObjectOutput fstOut = null;
        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            fstOut = new FSTObjectOutput(bytesOut);
            fstOut.writeObject(value);
            fstOut.flush();
            return bytesOut.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (fstOut != null)
                try {
                    fstOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public Object valueFromBytes(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return null;

        FSTObjectInput fstInput = null;
        try {
            fstInput = new FSTObjectInput(new ByteArrayInputStream(bytes));
            return fstInput.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (fstInput != null)
                try {
                    fstInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

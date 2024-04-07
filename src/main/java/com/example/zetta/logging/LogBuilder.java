package com.example.zetta.logging;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ObjectMessage;

import java.util.HashMap;
import java.util.Map;

public class LogBuilder
{

    private final String message;

    private final Map<String, Object> details;

    private Object additionalDetails;

    private LogBuilder(final String message)
    {
        this.message = message;
        this.details = new HashMap<>();
    }

    public static LogBuilder create(final String message)
    {
        return new LogBuilder(message);
    }

    public LogBuilder customerId(final long id)
    {
        return append("customerId", id);
    }

    public LogBuilder append(final String key, final Object value)
    {
        if (key != null)
        {
            details.put(key, value);
        }

        return this;
    }

    public LogBuilder additionalDetails(final Object additionalDetails)
    {
        this.additionalDetails = additionalDetails;
        return this;
    }

    public Message build()
    {
        Map<String, Object> result = new HashMap<>();
        result.put("formattedMessage", buildFormattedMessage());
        result.put("additionalDetails", additionalDetails);

        return new ObjectMessage(result);
    }

    private String buildFormattedMessage()
    {
        final String detailsMessage = details.isEmpty() ? "" : details.toString();
        return String.format("%s %s", message, detailsMessage);
    }
}

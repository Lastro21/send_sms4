package com.tech.send_sms.sendsms.convert;

import com.tech.send_sms.sendsms.dto.MessageInfo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringToMessageInfoConverterTest {

    private final StringToMessageInfoConverter converter = new StringToMessageInfoConverter();

    @Test
    public void convertMustReturnEmptyMessageInfoForNullString() {
        MessageInfo result = converter.convert(null);

        assertThat(result.getPhoneNumber(), is(""));
        assertThat(result.getText(), is(""));
    }

    @Test
    public void convertMustReturnEmptyMessageInfoForEmptyString() {
        MessageInfo result = converter.convert("");

        assertThat(result.getPhoneNumber(), is(""));
        assertThat(result.getText(), is(""));
    }

    @Test
    public void convertMustReturnExpectedMessageInfoForCorrectString() {
        final String expectedPhone = "84991234567";
        final String expectedText = "Test SMS";

        MessageInfo result = converter.convert(String.join(",", expectedText, expectedPhone));

        assertThat(result.getPhoneNumber(), is(expectedPhone));
        assertThat(result.getText(), is(expectedText));
    }
}
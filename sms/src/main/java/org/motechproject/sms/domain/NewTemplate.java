package org.motechproject.sms.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.sms.templates.Request;
import org.motechproject.sms.templates.Response;

/**
 * Represents the specifics about interacting with an SMS provider, not including the user identification particulars
 * which are stored in a NewConfig. Essentially a NewTemplate models how any user interacts with a particular SMS
 * provider whereas a NewConfig represents the details about how how a particular user interacts with their SMS provider
 */
@Entity
public class NewTemplate {

    @Field
    private String name;

    //====================
    // Outgoing properties
    //====================

    @Field
    private Request outgoingRequest;

    @Field
    private Response outgoingResponse;

    @Field
    private Boolean outgoingHasAuthentication;

    @Field
    private Boolean outgoingExponentialBackOffRetries;

    @Field
    private Integer outgoingMillisecondsBetweenMessages;

    @Field
    private Integer outgoingMaxSmsSize;

    @Field
    private Integer outgoingMaxRecipient;

    @Field
    private String outgoingRecipientSeparator;

    @Field
    private Integer outgoingDefaultMillisecondsBetweenMessages;

    @Field
    private Integer outgoingDefaultMaxSmsSize;

    @Field
    private Integer outgoingDefaultMaxRecipient;

    @Field
    private String outgoingDefaultRecipientSeparator;

    //====================
    // Incoming properties
    //====================

    @Field
    private String incomingMessageKey;

    @Field
    private String incomingSenderKey;

    @Field
    private String incomingSenderRegex;

    @Field
    private String incomingRecipientKey;

    @Field
    private String incomingRecipientRegex;

    @Field
    private String incomingTimestampKey;

    @Field
    private String incomingMsgIdKey;

}

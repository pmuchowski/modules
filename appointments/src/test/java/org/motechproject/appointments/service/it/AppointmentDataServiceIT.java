package org.motechproject.appointments.service.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.appointments.domain.Appointment;
import org.motechproject.appointments.repository.AppointmentDataService;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Verify that HelloWorldRecordService present, functional.
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class AppointmentDataServiceIT extends BasePaxIT {

    @Inject
    private AppointmentDataService appointmentDataService;

    @Test
    public void testHelloWorldRecordService() throws Exception {
        Appointment testRecord = new Appointment();
        testRecord.setExternalId("1234");
        appointmentDataService.create(testRecord);

        Appointment record = appointmentDataService.findAppointmentsByExternalId("1234").get(0);
        assertEquals(testRecord, record);
    }
}

package hongik.hospital.dummy;

import hongik.hospital.domain.doctor.Department;
import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.hospital.Address;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.Patient;

public class DummyObject {

    protected static Patient newPatient(String username, String name) {

        return Patient.builder()
                .username(username)
                .password("1234")
                .name(name)
                .gender(Gender.MAlE)
                .age(24L)
                .build();
    }

    protected static Doctor newDoctor(String username, String name) {
        return Doctor.builder()
                .username(username)
                .password("1234")
                .name(name)
                .department(Department.EYES)
                .career(10L)
                .build();
    }

    protected static Hospital newHospital(String username, String name, String city, String street) {
        Address address = new Address(city, street);

        return Hospital.builder()
                .username(username)
                .password("1234")
                .address(address)
                .name(name)
                .build();
    }
}

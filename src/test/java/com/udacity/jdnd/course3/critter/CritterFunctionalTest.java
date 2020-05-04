package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.PetController;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.schedule.ScheduleController;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a set of functional tests to validate the basic capabilities desired for this application.
 * Students will need to configure the application to run these tests by adding application.properties file
 * to the test/resources directory that specifies the datasource. It can run using an in-memory H2 instance
 * and should not try to re-use the same datasource used by the rest of the app.
 *
 * These tests should all pass once the project is complete.
 */
@Transactional
@SpringBootTest(classes = CritterApplication.class)
public class CritterFunctionalTest {

    @Autowired
    private UserController userController;

    @Autowired
    private PetController petController;

    @Autowired
    private ScheduleController scheduleController;

    @Test
    public void testCreateCustomer(){
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = userController.getAllCustomers().get(0);
        Assertions.assertEquals(newCustomer.getName(), customerDTO.getName());
        Assertions.assertEquals(newCustomer.getId(), retrievedCustomer.getId());
        Assertions.assertTrue(retrievedCustomer.getId() > 0);
    }

    @Test
    public void testCreateEmployee(){
        EmployeeDTO employeeDTO = createEmployeeDTO();
        EmployeeDTO newEmployee = userController.saveEmployee(employeeDTO);
        EmployeeDTO retrievedEmployee = userController.getEmployee(newEmployee.getId());
        Assertions.assertEquals(employeeDTO.getSkills(), newEmployee.getSkills());
        Assertions.assertEquals(newEmployee.getId(), retrievedEmployee.getId());
        Assertions.assertTrue(retrievedEmployee.getId() > 0);
    }

    @Test
    public void testAddPetsToCustomer() {
/*        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setCustomer(newCustomer);
        PetDTO newPet = petController.savePet(petDTO);*/

        //CustomerDTO customerDTO = createCustomerDTO();
        PetDTO petDTO = createPetDTO();
        petDTO.setCustomer(createCustomerDTO());

        PetDTO newPet = petController.savePet(petDTO);

        newPet.getCustomer().getPets().add(newPet);
        CustomerDTO newCustomer = userController.saveCustomer(newPet.getCustomer());

        //make sure pet contains customer id
        PetDTO retrievedPet = petController.getPet(newPet.getId());
        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getCustomer().getId(), newCustomer.getId());

        //make sure you can retrieve pets by owner
        List<PetDTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(newPet.getId(), pets.get(0).getId());
        Assertions.assertEquals(newPet.getName(), pets.get(0).getName());

        //check to make sure customer now also contains pet
        CustomerDTO retrievedCustomer = userController.getAllCustomers().get(0);
        Assertions.assertTrue(retrievedCustomer.getPets() != null && retrievedCustomer.getPets().size() > 0);
        Assertions.assertEquals(retrievedCustomer.getPets().get(0), retrievedPet.getId());
    }

    @Test
    public void testFindPetsByOwner() {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setCustomer(newCustomer);
        PetDTO newPet = petController.savePet(petDTO);

        petDTO.setType(PetType.DOG);
        petDTO.setName("DogName");
        PetDTO newPet2 = petController.savePet(petDTO);

        newCustomer.getPets().add(newPet2);
        CustomerDTO newCustomer2 = userController.saveCustomer(newCustomer);

        List<PetDTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(pets.size(), 2);
        Assertions.assertEquals(pets.get(0).getCustomer().getId(), newCustomer.getId());
        Assertions.assertEquals(pets.get(0).getId(), newPet.getId());
    }

    @Test
    public void testFindOwnerByPet() {
        CustomerDTO customerDTO = createCustomerDTO();

        PetDTO petDTO = createPetDTO();
        customerDTO.getPets().add(petDTO);
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);


        PetDTO newPetDTO = newCustomer.getPets().get(0);
        newPetDTO.setCustomer(newCustomer);

        long newCustomerPetId = newCustomer.getPets().get(0).getId();
        CustomerDTO owner = userController.getOwnerByPet(newCustomerPetId);
        Assertions.assertEquals(owner.getId(), newCustomer.getId());
        Assertions.assertEquals(owner.getPets().get(0).getId(), newCustomerPetId);
    }

    @Test
    public void testChangeEmployeeAvailability() {
        EmployeeDTO employeeDTO = createEmployeeDTO();
        EmployeeDTO emp1 = userController.saveEmployee(employeeDTO);
        Assertions.assertNull(emp1.getDaysAvailable());

        Set<DayOfWeek> availability = Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY);
        userController.setAvailability(availability, emp1.getId());

        EmployeeDTO emp2 = userController.getEmployee(emp1.getId());
        Assertions.assertEquals(availability, emp2.getDaysAvailable());
    }

    @Test
    public void testFindEmployeesByServiceAndTime() {
        EmployeeDTO emp1 = createEmployeeDTO();
        EmployeeDTO emp2 = createEmployeeDTO();
        EmployeeDTO emp3 = createEmployeeDTO();

        emp1.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        emp2.setDaysAvailable(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        emp3.setDaysAvailable(Sets.newHashSet(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

        emp1.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        emp2.setSkills(Sets.newHashSet(EmployeeSkill.PETTING, EmployeeSkill.WALKING));
        emp3.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        EmployeeDTO emp1n = userController.saveEmployee(emp1);
        EmployeeDTO emp2n = userController.saveEmployee(emp2);
        EmployeeDTO emp3n = userController.saveEmployee(emp3);

        //make a request that matches employee 1 or 2
        EmployeeRequestDTO er1 = new EmployeeRequestDTO();
        er1.setDate(LocalDate.of(2019, 12, 25)); //wednesday
        er1.setSkills(Sets.newHashSet(EmployeeSkill.PETTING));

        Set<Long> eIds1 = userController.findEmployeesForService(er1).stream().map(EmployeeDTO::getId).collect(Collectors.toSet());
        Set<Long> eIds1expected = Sets.newHashSet(emp1n.getId(), emp2n.getId());
        Assertions.assertEquals(eIds1, eIds1expected);

        //make a request that matches only employee 3
        EmployeeRequestDTO er2 = new EmployeeRequestDTO();
        er2.setDate(LocalDate.of(2019, 12, 27)); //friday
        er2.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        Set<Long> eIds2 = userController.findEmployeesForService(er2).stream().map(EmployeeDTO::getId).collect(Collectors.toSet());
        Set<Long> eIds2expected = Sets.newHashSet(emp3n.getId());
        Assertions.assertEquals(eIds2, eIds2expected);
    }

    @Test
    public void testSchedulePetsForServiceWithEmployee() {
        EmployeeDTO employeeTemp = createEmployeeDTO();
        employeeTemp.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        EmployeeDTO employeeDTO = userController.saveEmployee(employeeTemp);
        CustomerDTO customerDTO = userController.saveCustomer(createCustomerDTO());
        PetDTO petTemp = createPetDTO();
        petTemp.setCustomer(customerDTO);
        PetDTO petDTO = petController.savePet(petTemp);

        LocalDate date = LocalDate.of(2019, 12, 25);
        List<PetDTO> petList = Lists.newArrayList(petDTO);
        List<EmployeeDTO> employeeList = Lists.newArrayList(employeeDTO);
        Set<EmployeeSkill> skillSet =  Sets.newHashSet(EmployeeSkill.PETTING);

        scheduleController.createSchedule(createScheduleDTO(petList, employeeList, date, skillSet));
        ScheduleDTO scheduleDTO = scheduleController.getAllSchedules().get(0);

        Assertions.assertEquals(scheduleDTO.getActivities(), skillSet);
        Assertions.assertEquals(scheduleDTO.getDate(), date);
        Assertions.assertEquals(scheduleDTO.getEmployees(), employeeList);
        Assertions.assertEquals(scheduleDTO.getPets(), petList);
    }

    @Test
    public void testFindScheduleByEntities() {
        ScheduleDTO sched1 = populateSchedule(1, 2, LocalDate.of(2019, 12, 25), Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));
        ScheduleDTO sched2 = populateSchedule(3, 1, LocalDate.of(2019, 12, 26), Sets.newHashSet(EmployeeSkill.PETTING));

        //add a third schedule that shares some employees and pets with the other schedules
        ScheduleDTO sched3 = new ScheduleDTO();
        sched3.setEmployees(sched1.getEmployees());
        sched3.setPets(sched2.getPets());
        sched3.setActivities(Sets.newHashSet(EmployeeSkill.SHAVING, EmployeeSkill.PETTING));
        sched3.setDate(LocalDate.of(2020, 3, 23));
        scheduleController.createSchedule(sched3);

        /*
            We now have 3 schedule entries. The third schedule entry has the same employees as the 1st schedule
            and the same pets/owners as the second schedule. So if we look up schedule entries for the employee from
            schedule 1, we should get both the first and third schedule as our result.
         */

        //Employee 1 in is both schedule 1 and 3
        List<ScheduleDTO> scheds1e = scheduleController.getScheduleForEmployee(sched1.getEmployees().get(0).getId());
        compareSchedules(sched1, scheds1e.get(0));
        compareSchedules(sched3, scheds1e.get(1));

        //Employee 2 is only in schedule 2
        List<ScheduleDTO> scheds2e = scheduleController.getScheduleForEmployee(sched2.getEmployees().get(0).getId());
        compareSchedules(sched2, scheds2e.get(0));

        //Pet 1 is only in schedule 1
        List<ScheduleDTO> scheds1p = scheduleController.getScheduleForPet(sched1.getPets().get(0).getId());
        compareSchedules(sched1, scheds1p.get(0));

        //Pet from schedule 2 is in both schedules 2 and 3
        List<ScheduleDTO> scheds2p = scheduleController.getScheduleForPet(sched2.getPets().get(0).getId());
        compareSchedules(sched2, scheds2p.get(0));
        compareSchedules(sched3, scheds2p.get(1));

        //Owner of the first pet will only be in schedule 1
        List<ScheduleDTO> scheds1c = scheduleController.getScheduleForCustomer(userController.getOwnerByPet(sched1.getPets().get(0).getId()).getId());
        compareSchedules(sched1, scheds1c.get(0));

        //Owner of pet from schedule 2 will be in both schedules 2 and 3
        List<ScheduleDTO> scheds2c = scheduleController.getScheduleForCustomer(userController.getOwnerByPet(sched2.getPets().get(0).getId()).getId());
        compareSchedules(sched2, scheds2c.get(0));
        compareSchedules(sched3, scheds2c.get(1));
    }


    private static EmployeeDTO createEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("TestEmployee");
        employeeDTO.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        return employeeDTO;
    }
    private static CustomerDTO createCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("TestEmployee");
        customerDTO.setPhoneNumber("123-456-789");
        return customerDTO;
    }

    private static PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("TestPet");
        petDTO.setType(PetType.CAT);
        return petDTO;
    }

    private static EmployeeRequestDTO createEmployeeRequestDTO() {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setDate(LocalDate.of(2019, 12, 25));
        employeeRequestDTO.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));
        return employeeRequestDTO;
    }

    private static ScheduleDTO createScheduleDTO(List<PetDTO> pets, List<EmployeeDTO> employee, LocalDate date, Set<EmployeeSkill> activities) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setPets(pets);
        scheduleDTO.setEmployees(employee);
        scheduleDTO.setDate(date);
        scheduleDTO.setActivities(activities);
        return scheduleDTO;
    }

    private ScheduleDTO populateSchedule(int numEmployees, int numPets, LocalDate date, Set<EmployeeSkill> activities) {
        List<EmployeeDTO> employees = IntStream.range(0, numEmployees)
                .mapToObj(i -> createEmployeeDTO())
                .map(e -> {
                    e.setSkills(activities);
                    e.setDaysAvailable(Sets.newHashSet(date.getDayOfWeek()));
                    return userController.saveEmployee(e);
                }).collect(Collectors.toList());
        CustomerDTO customer = userController.saveCustomer(createCustomerDTO());
        List<PetDTO> pets = IntStream.range(0, numPets)
                .mapToObj(i -> createPetDTO())
                .map(p -> {
                    p.setCustomer(customer);
                    return petController.savePet(p);
                }).collect(Collectors.toList());
        return scheduleController.createSchedule(createScheduleDTO(pets, employees, date, activities));
    }

    private static void compareSchedules(ScheduleDTO sched1, ScheduleDTO sched2) {
        Assertions.assertEquals(sched1.getPets(), sched2.getPets());
        Assertions.assertEquals(sched1.getActivities(), sched2.getActivities());
        Assertions.assertEquals(sched1.getEmployees(), sched2.getEmployees());
        Assertions.assertEquals(sched1.getDate(), sched2.getDate());
    }

}

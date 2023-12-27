package org.png.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.png.entity.Bank;
import org.png.entity.Employee;
import org.png.repository.BankRepository;
import org.png.repository.EmployeeRepository;

import java.util.List;

@Path("/")
public class ManyToManyResource {

    @Inject
    private BankRepository bankRepository;

    @Inject
    private EmployeeRepository employeeRepository;

    @GET
    @Path("save_bank")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveBank(){
        String[] bankNames = {"SBI","PNB","AXIS","ICICI","HDFC","KOTAK"};
        for(String bankName: bankNames){
            Bank bank = new Bank();
            bank.setName(bankName);
            bank.setBranch("IT Park ");
            bank.setIfscCode("IFSC22"+bankName);
            bankRepository.persist(bank);
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("save_employee")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveEmployee(){
        String[] bankNames = {"SBI","PNB","AXIS","ICICI","HDFC","KOTAK"};

        Bank SBIBank = bankRepository.find("name", bankNames[0]).firstResult();
        Bank PNBBank = bankRepository.find("name", bankNames[1]).firstResult();
        Bank AXISBank = bankRepository.find("name", bankNames[2]).firstResult();
        Bank ICICIBank = bankRepository.find("name", bankNames[3]).firstResult();
        Bank HDFCBank = bankRepository.find("name", bankNames[4]).firstResult();
        Bank KOTAKBank = bankRepository.find("name", bankNames[5]).firstResult();

        Employee employeeRahul = new Employee();
        employeeRahul.setName("Rahul");
        employeeRahul.setGender("M");
        employeeRahul.setBankList(List.of(SBIBank, AXISBank, ICICIBank, KOTAKBank));

        Employee employeeAaka = new Employee();
        employeeAaka.setName("Aaka");
        employeeAaka.setGender("F");
        employeeAaka.setBankList(List.of(SBIBank, AXISBank, ICICIBank, HDFCBank));

        Employee employeeMic = new Employee();
        employeeMic.setName("Mic");
        employeeMic.setGender("M");
        employeeMic.setBankList(List.of(AXISBank, ICICIBank));

        employeeRepository.persist(employeeRahul);
        employeeRepository.persist(employeeAaka);
        employeeRepository.persist(employeeMic);

        return Response.status(Response.Status.OK).build();
    }


}

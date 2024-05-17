package petStore.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import petStore.entity.Employee;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
	
	public PetStoreEmployee(Employee employee) 
	{
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeePhoneNumber = employee.getEmployeePhoneNumber();
		employeeJobTitle = employee.getEmployeeJobTitle();
	}
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhoneNumber;
	private String employeeJobTitle;

}

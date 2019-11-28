package com.nishh.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.nishh.model.Employee;

public class EmpUtils {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		// ******************************************************************************************************//
		// 1. How many male and female employees are there in the organization?

		long maleCount = employeeList.stream().filter(p -> p.getGender().equalsIgnoreCase("MALE")).count();
		long femaleCount = employeeList.stream().filter(p -> p.getGender().equalsIgnoreCase("Female")).count();

		System.out.println("Male employee --->" + maleCount);
		System.out.println("Female employee --->" + femaleCount);

		System.out.println("lets see a better approach");

		Map<String, Long> noOfEmpl = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

		System.out.println(noOfEmpl);

		System.out.println("********************************************************************");
		// ******************************************************************************************************//
		// 2. Print the name of all departments in the organization?
		employeeList.stream().map(m -> m.getDepartment()).forEach(System.out::println);

		System.out.println("lets have distinct department");

		System.out.println("********************************************************************");
		employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

		// ******************************************************************************************************//

		// 3. What is the average age of male and female employees?
		Map<String, Double> avgAgeOfMaleFemaleEmployee = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

		System.out.println(avgAgeOfMaleFemaleEmployee);

		// 4.Get the details of highest paid employee in the organization?

		System.out.println("********************************************************************");
		Optional<Employee> highestPaidEmployee = employeeList.stream()
				.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));

		System.out.println("Highly paid employee is");

		System.out.println(highestPaidEmployee.get().getName());

		System.out.println("********************************************************************");
		// 5.Get the names of all employees who have joined after 2015?

		employeeList.stream().filter(p -> p.getYearOfJoining() > 2015).map(Employee::getName)
				.forEach(System.out::println);

		System.out.println("********************************************************************");

		// 6. Count the number of employees in each department?

		Map<String, Long> noOfEmployeeIneachDepartment = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		Set<Entry<String, Long>> enSet = noOfEmployeeIneachDepartment.entrySet();

		for (Entry<String, Long> entry : enSet) {
			System.out.println(entry.getKey() + " :" + entry.getValue());
		}

		System.out.println("********************************************************************");

		// 7. What is the average salary of each department?
		Map<String, Double> abgSalIneachDepartment = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		Set<Entry<String, Double>> entitySet = abgSalIneachDepartment.entrySet();

		for (Entry<String, Double> entry : entitySet) {
			System.out.println(entry.getKey() + " :" + entry.getValue());
		}

		System.out.println("********************************************************************");

		// 8 Get the details of youngest male employee in the product development
		// department?

		Optional<Employee> youngestEmployee = employeeList.stream()
				.filter(predicate -> predicate.getGender().equalsIgnoreCase("Male")
						&& predicate.getDepartment().equalsIgnoreCase("product development"))
				.collect(Collectors.minBy((Comparator.comparingDouble(Employee::getAge))));

		System.out.println("Youngest employee is");

		System.out.println(youngestEmployee.get().getName());

		System.out.println("********************************************************************");

		// 9. Who has the most working experience in the organization?

		Optional<Employee> mostWorkingEmployee = employeeList.stream()
				.sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();

		System.out.println("Most Experienced employee is");

		System.out.println(mostWorkingEmployee.get().getName());

		System.out.println("********************************************************************");
		// 10.How many male and female employees are there in the sales and marketing
		// team?

		Map<String, Long> countMaleFemaleEmployeesInSalesMarketing = employeeList.stream()
				.filter(predicate -> predicate.getDepartment().equalsIgnoreCase("sales and marketing"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(countMaleFemaleEmployeesInSalesMarketing);
		System.out.println("********************************************************************");
		// 11.What is the average salary of male and female employees?

		Map<String, Double> avgSalOfMaleAndFemale = employeeList.stream()

				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));

		System.out.println(avgSalOfMaleAndFemale);

		// 12 List down the names of all employees in each department?
		Map<String, List<Employee>> nameOfAllEmployeeInEachDepartment = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		Set<Entry<String, List<Employee>>> entries = nameOfAllEmployeeInEachDepartment.entrySet();
		for (Entry<String, List<Employee>> entriesForEmpl : entries) {

			System.out.println(entriesForEmpl.getKey() + "_________________" + entriesForEmpl.getValue());

		}

	}
}

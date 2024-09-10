package com.twenty4.WebApp.repository;

import com.twenty4.WebApp.entity.Timesheet;
import com.twenty4.WebApp.model.EmployeeHours;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

        @Query("SELECT new map(e.name as employee, SUM(t.mondayHours + t.tuesdayHours + t.wednesdayHours + t.thursdayHours + t.fridayHours + t.saturdayHours + t.sundayHours) as totalHours) "
                        +
                        "FROM Timesheet t " +
                        "JOIN t.employee e " +
                        "WHERE t.weekEnding = :weekEnding " +
                        "GROUP BY e.name")
        List<Map<String, Object>> getEmployeeHoursByWeekEnding(@Param("weekEnding") LocalDate weekEnding);

        @Query("SELECT new com.twenty4.WebApp.model.EmployeeHours(e.name, SUM(t.mondayHours + t.tuesdayHours + t.wednesdayHours + t.thursdayHours + t.fridayHours + t.saturdayHours + t.sundayHours)) "
                        +
                        "FROM Timesheet t " +
                        "JOIN t.employee e " +
                        "WHERE t.weekEnding = :weekEnding " +
                        "GROUP BY e.name")
        List<EmployeeHours> findEmployeeHoursByWeekEnding(@Param("weekEnding") LocalDate weekEnding);
}

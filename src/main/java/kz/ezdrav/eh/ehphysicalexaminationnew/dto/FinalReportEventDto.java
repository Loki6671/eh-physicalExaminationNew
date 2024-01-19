package kz.ezdrav.eh.ehphysicalexaminationnew.dto;


import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinalReportEventDto {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int totalEmployees;
    private int totalFemaleEmployees;
    private int employeesWithHazardsTotal; // 8.1 Работающих с вредными условиями всего
    private int employeesWithHazardsFemale; // 8.2 Работающих с вредными условиями женщин
    private int employeesForMedicalExaminationTotal; // 9.1 Подлежащих медосмотру всего
    private int employeesForMedicalExaminationFemale; // 9.2 Подлежащих медосмотру женщин
    private int employeesExaminedTotal; // 10.1 Прошедших медосмотр всего
    private int employeesExaminedFemale; // 10.2 Прошедших медосмотр женщин
    private float examinationCoverageTotal; // 11.1 % охвата медосмотрами всего
    private float examinationCoverageFemale; // 11.2 % охвата медосмотрами женщин
    private int employeesNotFinishedExaminationTotal; // 12.1 Не завершившие медосмотр всего
    private int employeesNotFinishedExaminationFemale; // 12.2 Не завершившие медосмотр женщин
//    private List<EmployeeNotFinishedExamination> employeesNotFinishedExaminationList; // 12.3 Поименный список
    private int employeesNotExaminedTotal; // 14.1 Не прошедшие осмотр всего
    private int employeesNotExaminedFemale; // 14.2 Не прошедшие осмотр женщин
    private int employeesNotExaminedSickLeave; // 14.3.1 Не прошедшие осмотр - больничный
    private int employeesNotExaminedBusinessTrip; // 14.3.2 Не прошедшие осмотр - командировка
    private int employeesNotExaminedVacation; // 14.3.3 Не прошедшие осмотр - отпуск
    private int employeesNotExaminedDismissal; // 14.3.4 Не прошедшие осмотр - увольнение
    private int employeesNotExaminedRefusal; // 14.3.5 Не прошедшие осмотр - отказ

}

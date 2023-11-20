package com.ssafy.ssafit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSetting {
    @Id
    @Column(nullable = false, name = "alramsetting_user")
    private String user;

    @Column(name = "alramsetting_date")
    private String date;
}

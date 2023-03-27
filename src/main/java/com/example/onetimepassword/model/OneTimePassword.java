package com.example.onetimepassword.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pat on 27-Mar-23 - 9:28 AM
 *
 * @author pat
 * @project one-time-password
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class OneTimePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @NonNull
    private Integer oneTimePasswordCode;
    @NonNull
    private Date expires;
}
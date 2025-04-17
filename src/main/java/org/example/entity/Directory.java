package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "directory")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Directory extends Auditable {
    @Id
    @Column(name = "directory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directoryId;

    @Column(name = "count_files")
    @NotNull
    @Min(value = 0)
    private Integer countFiles;

    @Column(name = "department")
    @NotNull
    private String department;

    @Column(name = "directory_name")
    @NotNull
    private String directoryName;

    @Column(name = "directory_size")
    @Min(value = 0)
    private Long directorySize;

    @OneToMany(mappedBy = "directory")
    private List<File> files;
}

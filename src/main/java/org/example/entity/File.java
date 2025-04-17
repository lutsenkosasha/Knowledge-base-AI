package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "file")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class File extends Auditable {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    @Column(name = "file_name")
    @NotNull
    private String fileName;

    @Column(name = "file_size")
    @NotNull
    @Min(value = 0)
    private Long fileSize;

    @ManyToOne()
    @JoinColumn(name = "directory_id")
    private Directory directory;
}

package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private Long fileId;

    @Column(name = "file_name")
    @NotNull
    private String fileName;

    @Column(name = "file_size")
    @ColumnDefault("0")
    @Min(value = 0)
    private Long fileSize;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @PrePersist
    public void prePersist(){
        if(fileSize == null)
            fileSize = 0L;
    }
}

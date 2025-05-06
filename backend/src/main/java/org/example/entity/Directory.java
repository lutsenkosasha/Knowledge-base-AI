package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "directory")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Directory extends Auditable {
    @Id
    @Column(name = "directory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directoryId;

    @Column(name = "count_files")
    @ColumnDefault("0")
    @Min(value = 0)
    private Integer countFiles;

    @Column(name = "department")
    @NotNull
    private String department;

    @Column(name = "directory_name")
    @NotNull
    private String directoryName;

    @Column(name = "directory_size")
    @ColumnDefault("0")
    @Min(value = 0)
    private Long directorySize;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<File> files;

    @PrePersist
    public void prePersist(){
        if(directorySize == null)
            directorySize = 0L;
        if(countFiles == null)
            countFiles = 0;
    }
}

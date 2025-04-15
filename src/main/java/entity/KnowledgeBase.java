package entity;
import javax.persistence.*;

@Entity
@Table(name = "knowledge_base")
public class KnowledgeBase extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "knowledge_base_id")
    private Long knowledgeBaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id")
    private Directory directory;

    public Long getKnowledgeBaseId() {
        return knowledgeBaseId;
    }

    public void setKnowledgeBaseId(Long knowledgeBaseId) {
        this.knowledgeBaseId = knowledgeBaseId;
    }

    public Directory getDirectory() { return directory; }

    public void setDirectory(Directory directory) { this.directory = directory; }
}
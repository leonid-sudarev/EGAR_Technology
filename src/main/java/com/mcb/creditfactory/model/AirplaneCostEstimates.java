package com.mcb.creditfactory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AIRPLANE_COST_ESTIMATES")
public class AirplaneCostEstimates implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ESTIMATES_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Positive
  @Column(name = "COST_ESTIMATES")
  private BigDecimal value;

  @Basic
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "DATE_OF_ESTIMATE")
  @Temporal(TemporalType.DATE)
  private java.util.Date date;

  @ManyToOne(targetEntity = Airplane.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "AIRPLANE_ID")
  private Airplane airplane;

  @Override
  public String toString() {
    return "AirplaneCostEstimates{" +
        "id=" + id +
        ", value=" + value +
        ", date=" + date +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AirplaneCostEstimates)) {
      return false;
    }

    AirplaneCostEstimates that = (AirplaneCostEstimates) o;

    if (!value.equals(that.value)) {
      return false;
    }
    if (!date.equals(that.date)) {
      return false;
    }
    return airplane.equals(that.airplane);
  }

  @Override
  public int hashCode() {
    int result = value.hashCode();
    result = 31 * result + date.hashCode();
    result = 31 * result + airplane.hashCode();
    return result;
  }
}

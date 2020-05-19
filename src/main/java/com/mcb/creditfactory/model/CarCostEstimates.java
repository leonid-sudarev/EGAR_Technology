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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CAR_COST_ESTIMATES")
public class CarCostEstimates implements Serializable {
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

  @ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "CAR_ID")
  private Car car;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CarCostEstimates)) {
      return false;
    }

    CarCostEstimates that = (CarCostEstimates) o;

    if (!value.equals(that.value)) {
      return false;
    }
    if (!date.equals(that.date)) {
      return false;
    }
    return car.equals(that.car);
  }

  @Override
  public int hashCode() {
    int result = value.hashCode();
    result = 31 * result + date.hashCode();
    result = 31 * result + car.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "CarCostEstimates{" +
        "id=" + id +
        ", value=" + value +
        ", date=" + date +
        '}';
  }
}

package com.mcb.creditfactory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;


@Entity
@Getter
@Setter
@Table(name = "CAR")
public class Car implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CAR_ID")
  private Long id;

  @Column(name = "BRAND")
  private String brand;

  @Column(name = "MODEL")
  private String model;

  @Column(name = "POWER")
  private Double power;

  @Column(name = "YEAR_OF_ISSUE")
  private Short year;

  @Positive
  @Transient
  private BigDecimal value;

  @Transient
  private  java.util.Date dateOfEstimate ;


  @OneToMany(
      targetEntity = CarCostEstimates.class,
      mappedBy = "car",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL )
  private List<CarCostEstimates> carCostEstimates = new ArrayList<>();

  @Transactional
  public void addCarCostEstimates(CarCostEstimates estimates){
    this.carCostEstimates.add(estimates);
    estimates.setCar(this);
  }

  @Override
  public String toString() {
    return "Car{" +
        "id=" + id +
        ", year=" + year +
        ", value=" + value +
        ", dateOfEstimate=" + dateOfEstimate +
        ", carCostEstimates=" + carCostEstimates +
        '}';
  }

  public Car() {
  }

  public Car(Long id, String brand, String model, Double power, Short year, BigDecimal value, java.util.Date dateOfEstimate) {
  this.id=id;
  this.brand =brand;
  this.model = model;
  this.power = power;
  this.year = year;
  this.value = value;
  this.dateOfEstimate=dateOfEstimate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Car)) {
      return false;
    }

    Car car = (Car) o;

    if (brand != null ? !brand.equals(car.brand) : car.brand != null) {
      return false;
    }
    if (model != null ? !model.equals(car.model) : car.model != null) {
      return false;
    }
    if (power != null ? !power.equals(car.power) : car.power != null) {
      return false;
    }
    if (year != null ? !year.equals(car.year) : car.year != null) {
      return false;
    }
    if (!value.equals(car.value)) {
      return false;
    }
    return dateOfEstimate.equals(car.dateOfEstimate);
  }

  @Override
  public int hashCode() {
    int result = brand != null ? brand.hashCode() : 0;
    result = 31 * result + (model != null ? model.hashCode() : 0);
    result = 31 * result + (power != null ? power.hashCode() : 0);
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + value.hashCode();
    result = 31 * result + dateOfEstimate.hashCode();
    return result;
  }
}

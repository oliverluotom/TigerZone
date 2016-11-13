public class Field extends Region{

  protected int completedCities;

  public Field(){
    completedCities = 0;
  }

  public void absorb(Region otherRegion){
    Field otherField = (Field) otherRegion;
    completedCities += otherField.completedCities;
  }

  public void addCompleteCity(){
    completedCities++;
  }
}

import java.sql.SQLException;

import com.andreas.basicApp.model.AgeCategory;
import com.andreas.basicApp.model.Database;
import com.andreas.basicApp.model.EmploymentCategory;
import com.andreas.basicApp.model.Gender;
import com.andreas.basicApp.model.Person;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Testing Database");

		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		db.addPerson(new Person("Albert", "Architect", AgeCategory.adult,
				EmploymentCategory.employed, "TT11A", true, Gender.m));

		db.addPerson(new Person("Sue", "Artist", AgeCategory.child,
				EmploymentCategory.selfEmployed, null, false, Gender.w));

		try {
			db.save();
			db.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.disconnect();
	}
}

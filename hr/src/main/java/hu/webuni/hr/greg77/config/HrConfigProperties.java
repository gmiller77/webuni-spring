package hu.webuni.hr.greg77.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {

	private Raise raise = new Raise();

	public Raise getRaise() {
		return raise;
	}

	public void setRaise(Raise raise) {
		this.raise = raise;
	}

	public static class Raise {
		private DefaultRaise def = new DefaultRaise();
		private SmartRaise smart = new SmartRaise();

		public DefaultRaise getDef() {
			return def;
		}

		public void setDef(DefaultRaise def) {
			this.def = def;
		}

		public SmartRaise getSmart() {
			return smart;
		}

		public void setSmart(SmartRaise smart) {
			this.smart = smart;
		}
	}

	public static class DefaultRaise {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}

	public static class SmartRaise {
		private int defaultPercent;
		private Map<String, SmartRaisePercent> percents;

		public int getDefaultPercent() {
			return defaultPercent;
		}

		public void setDefaultPercent(int defaultPercent) {
			this.defaultPercent = defaultPercent;
		}

		public Map<String, SmartRaisePercent> getPercents() {
			return percents;
		}

		public void setPercents(Map<String, SmartRaisePercent> percents) {
			this.percents = percents;
		}

		@Override
		public String toString() {
			StringBuilder mapAsString = new StringBuilder("{");
			for (String key : this.percents.keySet()) {
				mapAsString.append(key + "=" + percents.get(key).getLimit() + ", ");
			}
			mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("}");
			return mapAsString.toString();
		}
	}

	public static class SmartRaisePercent {

		private int limit; // limit in MONTHS, like 10 years = 120 months etc.
		private int percent;

		public int getLimit() {
			return limit;
		}

		public void setLimit(int limit) {
			this.limit = limit;
		}

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

	}

}

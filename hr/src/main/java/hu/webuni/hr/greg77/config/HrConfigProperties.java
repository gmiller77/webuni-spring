package hu.webuni.hr.greg77.config;

import java.util.List;

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
		private List<Integer> limits;
		private List<Integer> percents;
		private int defaultPercent;

		public List<Integer> getLimits() {
			return limits;
		}

		public void setLimits(List<Integer> limits) {
			this.limits = limits;
		}

		public List<Integer> getPercents() {
			return percents;
		}

		public void setPercents(List<Integer> percents) {
			this.percents = percents;
		}

		public int getDefaultPercent() {
			return defaultPercent;
		}

		public void setDefaultPercent(int defaultPercent) {
			this.defaultPercent = defaultPercent;
		}

	}

}

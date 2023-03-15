package hu.webuni.hr.greg77.config;

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
		private int limit1;
		private int limit2;
		private int limit3;
		private int percent1;
		private int percent2;
		private int percent3;
		private int defaultPercent;

		public int getLimit1() {
			return limit1;
		}

		public void setLimit1(int limit1) {
			this.limit1 = limit1;
		}

		public int getLimit2() {
			return limit2;
		}

		public void setLimit2(int limit2) {
			this.limit2 = limit2;
		}

		public int getLimit3() {
			return limit3;
		}

		public void setLimit3(int limit3) {
			this.limit3 = limit3;
		}

		public int getPercent1() {
			return percent1;
		}

		public void setPercent1(int percent1) {
			this.percent1 = percent1;
		}

		public int getPercent2() {
			return percent2;
		}

		public void setPercent2(int percent2) {
			this.percent2 = percent2;
		}

		public int getPercent3() {
			return percent3;
		}

		public void setPercent3(int percent3) {
			this.percent3 = percent3;
		}

		public int getDefaultPercent() {
			return defaultPercent;
		}

		public void setDefaultPercent(int defaultPercent) {
			this.defaultPercent = defaultPercent;
		}

	}

}

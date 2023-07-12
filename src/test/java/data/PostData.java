package data;

import lombok.Getter;
import lombok.Setter;

public class PostData {
    @Getter
    @Setter

        private final String name;
        private final String job;


        public PostData (final String name, final String job) {
            this.name = name;
            this.job = job;

        }
    }


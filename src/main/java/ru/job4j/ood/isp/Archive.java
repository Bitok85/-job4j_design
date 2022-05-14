package ru.job4j.ood.isp;

/**
 * Допустим и для отчётов и для презентаций нам необходимо архивировать информацию. Соответственно такой общий интерфейс
 * будет "подвешивать" ненужные в конкретном классе методы. В данном случае естественным будет создание интерфейсов для
 * каждого архиватора в отдельности.
 */

public interface Archive {

    void winZip();

    void winRar();

    void winAce();
}

class Reports implements Archive {

    @Override
    public void winZip() {

    }

    @Override
    public void winRar() {

    }

    @Override
    public void winAce() {

    }
}

    class Presentation implements Archive {

        @Override
        public void winZip() {

        }

        @Override
        public void winRar() {

        }

        @Override
        public void winAce() {

        }
    }


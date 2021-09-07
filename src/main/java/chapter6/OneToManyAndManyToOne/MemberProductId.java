package chapter6.OneToManyAndManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class MemberProductId implements Serializable {

    private String member;
    private String product;

    public void setMember(String member) {
        this.member = member;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberProductId that = (MemberProductId) o;
        return Objects.equals(member, that.member) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }
}

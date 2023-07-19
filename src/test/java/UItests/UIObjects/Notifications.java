package UItests.UIObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Notifications extends Header{
    private final SelenideElement notificationModal = $("#modal-box");

    public SelenideElement getNotificationModal() {
        return notificationModal;
    }
}

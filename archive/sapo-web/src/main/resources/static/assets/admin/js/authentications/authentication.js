"use strict";

const $ = (selector) => document.querySelector(selector);

(async () => {
    try {
        const getForm = await document.querySelectorAll(".needs-validation");

        await Array
        .from(getForm)
        .forEach((form) => {
            form.addEventListener("submit", async (event) => {
                if (!form.checkValidity()) {
                    await event.preventDefault();
                    await event.stopPropagation();
                }

                await form.classList.add("was-validated");
            }, false);
        });

    } catch (error) {
        throw new Error("An error occurred: " + error);
    }
})();

const passwordToggleEventHandler = async (selectorInput, selectorShow, selectorHide) => {
    try {
        const getSelectorInput = await $(selectorInput);
        const getSelectorShow = await $(selectorShow);
        const getSelectorHide = await $(selectorHide);

        await getSelectorShow.addEventListener("click", async () => {
            const getInputType = getSelectorInput.getAttribute("type") === "password" ? "text" : "password";

            await getSelectorInput.setAttribute("type", getInputType);
            await getSelectorShow.classList.add("hidden");
            await getSelectorHide.classList.remove("hidden");
        });

    } catch (error) {
        throw new Error("An error occurred: " + error);
    }
}

const passwordToggleEventIntermediary = async (selectorInput, selectorShow, selectorHide) => {
    try {
        await Promise.all([
            passwordToggleEventHandler(selectorInput, selectorShow, selectorHide),
            passwordToggleEventHandler(selectorInput, selectorHide, selectorShow)
        ]);

    } catch (error) {
        throw new Error("An error occurred: " + error);
    }
}

passwordToggleEventIntermediary("#login-password", "#login-icon-eye-id", "#login-icon-eye-slash-id");
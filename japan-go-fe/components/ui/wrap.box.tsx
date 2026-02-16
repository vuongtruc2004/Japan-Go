import React from "react";

const WrapBox = ({ children }: { children: React.ReactNode }) => {
    return (
        <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
            {children}
        </div>
    );
};

export default WrapBox;

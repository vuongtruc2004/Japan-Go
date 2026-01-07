"use client";
import { ProgressProvider } from "@bprogress/next/app";

const BProgressWrapper = ({ children }: { children: React.ReactNode }) => {
    return (
        <ProgressProvider
            height="4px"
            color="var(--color-bgc-highlight)"
            options={{ showSpinner: false }}
            shallowRouting
        >
            {children}
        </ProgressProvider>
    );
};

export default BProgressWrapper;
